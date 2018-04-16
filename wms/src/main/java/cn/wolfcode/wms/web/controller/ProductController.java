package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
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
    private ServletContext ctx; //网站的上下文

    @RequestMapping("list")
    public String list(@ModelAttribute("qo") QueryObject qo, Model model) throws Exception {
        model.addAttribute("result", productService.query(qo));
        return "product/list";
    }

    @RequestMapping("input")
    public String input(Long id, Model model) throws Exception {
        if (id != null) {
            model.addAttribute("entity", productService.get(id));
        }
        model.addAttribute("brands", brandService.list());
        return "product/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Product entity, MultipartFile pic) throws Exception {
        //处理上传过来的文件
        if (pic != null && pic.getSize() > 0 && pic.getContentType().startsWith("image/")) {
            //删除该对象旧的图片
            if (StringUtils.hasLength(entity.getImagePath())) {
                UploadUtil.deleteFile(ctx, entity.getImagePath());
            }

            //把文件保存到服务器中
            String dir = ctx.getRealPath("/upload");
            String imagePath = UploadUtil.upload(pic, dir);
            //把图片服务器路径保存到对象中
            entity.setImagePath(imagePath);
        }
        //查询品牌对于的名称
        Brand brand = brandService.get(entity.getBrandId());
        entity.setBrandName(brand.getName());
        productService.saveOrUpdate(entity);
        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) throws Exception {
        if (id != null) {
            productService.delete(id);
        }
        return new JSONResult();
    }
}
