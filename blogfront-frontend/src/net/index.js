import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error('发生一些错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)

//封装post
function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        //因为是通过表单提交的 所以需要将Content-Type改成表单的形式
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        //发起请求是否携带Cookie
        withCredentials: true
        //then 处理数据
    }).then(({data}) => {
        if (data.success) {
        success(data.message, data.status)
    }
else
    {
        failure(data.message, data.status)
    }
}
).
catch(error)
}

//封装get
function get(url, success, failure =  defaultFailure , error =  defaultError) {
    axios.get(url, {
        //发起请求是否携带Cookie
        withCredentials: true
        //then 处理数据
    }).then(({data}) => {
        if (data.success)
            success(data.message, data.status)
        else
            failure(data.message, data.status)
    }).catch(error)
}

//导出去
export {get, post}