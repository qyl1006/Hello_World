var employee = {
    id : 10,
    username : "王尼玛",
    age : 18,
    hobby : ["Java", "JavaScript", "gril"],
    dept : {
        id : 22,
        name : "开发部",
    }
}

console.log(employee);
console.log(employee.dept.name);

var arr = [
    {
        id : 10,
        username : "王尼玛",
        age : 18,
        hobby : ["Java", "JavaScript", "gril"],
        dept : {
            id : 22,
            name : "开发部",
        }
    },
    {
        id : 11,
        username : "赵铁柱",
        age : 18,
        hobby : ["装机", "拆机"],
        dept : {
            id : 11,
            name : "维修部",
        }
    }
]

console.log(arr[1].username);
console.log(arr[1].dept.name);