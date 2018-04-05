//全部右移
function moveAll(s1, s2) {
    var s1 = document.getElementsByTagName("s1");
    var s2 = document.getElementsByTagName("s1");

    if(s1.length > s2.length){
        for (var i = s1.length -1; i >= 0; i --){
            s2.appendChild(s1[i])
        }
    }
}

