package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private ServletContext ctx;  //网站的上下文



    @RequestMapping("list")
    public String list(@ModelAttribute("qo") ProductQueryObject qo, Model model){


        PageResult result = productService.queryAll(qo);
        model.addAttribute("result", result);

        //所有品牌
        model.addAttribute("brands", brandService.listAll());

        return "product/list";
    }


    @RequestMapping("productListViews")
    public String productListViews(@ModelAttribute("qo") ProductQueryObject qo, Model model){
        qo.setPageSize(20);

        PageResult result = productService.queryAll(qo);
        model.addAttribute("result", result);

        //所有品牌
        model.addAttribute("brands", brandService.listAll());

        return "product/productListViews";
    }

    @RequestMapping("input")
    public String input(Long id, Model model){
        if (id != null) {
            Product entity = productService.getById(id);
            model.addAttribute("entity",entity);
        }


        //所有品牌
        model.addAttribute("brands", brandService.listAll());
        return "product/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Product entity, MultipartFile pic){
        //处理上传过来的文件图片
        if(pic != null && pic.getSize() > 0 && pic.getContentType().startsWith("image/")){
            //删除该对象旧的图片
            if (entity.getImagePath() != null){
                UploadUtil.deleteFile(ctx, entity.getImagePath());
            }

            //把文件保存到服务器
           String dir = ctx.getRealPath("/upload");
           String imagePath = UploadUtil.upload(pic, dir);

           //把图片的服务器路径保存到对象中
            entity.setImagePath(imagePath);
        }

        //查询品牌对应的名称
        Brand brand = brandService.getById(entity.getBrandId());
        entity.setBrandName(brand.getName());

        productService.insertOrUpdate(entity);

        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id, String imagePath){
        if (id != null) {
            productService.deleteById(id);
            if(StringUtils.hasLength(imagePath)){
                UploadUtil.deleteFile(ctx, imagePath);
            }
        }

        return new JSONResult();
    }

}
