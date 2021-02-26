package com.cy.pj.common.pojo;

/**
 * 通过此对象封装服务端响应到客户端的数据,让数据以一种规范化的格式呈现给客户端.
 */
public class JsonResult {
    /**状态码*/
    private Integer state=1;//1 表示OK,0表示Error
    /**状态码信息*/
    private String message="ok";
    /**封装正确的查询结果*/
    private Object data;

    public JsonResult(){}
    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Integer state, String message){
        this(message);
        this.state=state;
    }
    public JsonResult(Object data){//new JsonResult(list)
        this.data=data;
    }
    //当出现异常时,可以通过此构造方法对异常信息进行封装
    public JsonResult(Throwable exception){//new JsonResult(exception);
        this(0,exception.getMessage());
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {

        this.data = data;
    }
}
