//域名
// var the_path="http://localhost:8080"
var the_path=location.protocol+"//"+location.host;

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    config.url=the_path+config.url;
    if ((config.method==="get"||config.method==="delete")&&config.data){
        config.params=config.data;
        config.data=null;
    }
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (res) {
    // 2xx 范围内的状态码都会触发该函数。
    if (res.data.code===0&&res.data.msg==="NOT_LOG_IN"){
        if (window.parent!==null){
            window.parent.location.href=the_path+"/html/login.html";
        }else{
            window.location.href=the_path+"/html/login.html"
        }
    }else if (res.data.code===0){
        window.parent!=null?window.parent.app.$message.error(res.data.msg):
            window.app.$message.error(res.data.data);
    }
    return res;
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

//映射人物职位
var get_position=function (i){
    return i===0?"管理员":
        i===1?"林长":
            i===2?"监管员":
                i===3?"巡护员":"非法";
}

//映射人物状态
var get_status=function (i){
    return i===0?"空闲":
        i===1?"任务中":"非法";
}

//获取年月日日期
var get_time=function (d){
    date=new Date(d);
    return date.getFullYear()+" "+date.getMonth()+" "+date.getDate();
}

//获取月日时分
var get_time_month_minutes=function (b){
    date=new Date(b);
    return date.getMonth()+"/"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()
}

//给名字获取图片全路径
var get_img=function (name){
    return the_path+"/download_img/"+name;
}

/**
 * 大于position判断
 */
var gt_position=function (i){
    if (localStorage.getItem("id")==="1")return true;
    return i>localStorage.getItem("position");
}
/**
 * id是否相同
 */
var eq_pi_id=function (id){
    return id===localStorage.getItem("id");
}
