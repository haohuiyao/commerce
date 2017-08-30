/**
 * Created by Administrator on 2017/7/12.
 * create by daipengpeng
 * 公用方法
 */


var common = {

    toast : function(type,errMsg)
    {
        var bgColor = (type == 0) ? "red" : "green";
        var imgSrc = (type == 0) ? 'build/images/public/no.png' : 'build/images/public/yes.png';

        var html = '';

        html += '<div class="toast '+bgColor+'">';
        html += '<img src="'+imgSrc+'"/>'+errMsg+'';
        html += '</div>';

        $('body').append(html);
        $(".toast").fadeIn();

        setTimeout(function(){
            $(".toast").remove();
        },1500);
    },

    /**
     * 公用提示框
     * @param {Object} errMsg
     */
    alert : function(errMsg)
    {
        if($(".alert").length > 0)
        {
            return;
        }

        var html = '';

        html += '<div class="alert">';
        html += '<div class="alert-header">';
        html += '<h2>提示信息</h2>';
        html += '</div>';
        html += '<div class="alert-con"><h3>'+errMsg+'</h3></div>';
        html += '</div>';

        $("body").append(html);

        $(".alert").show();

        setTimeout(function(){
            $(".alert").remove();
        },1000);
    },

    /**
     * 打开模态框
     * @param {Object} id
     */
    showModal : function(id){

        $("#" + id).show();

        $(".modal-main").addClass("animated fadeInDown");
        $(".modal-ms").addClass("animated fadeInDown");
        $(".modal-test").addClass("animated fadeInDown");

        $(".modal-header span").on("click",function(){
            common.hideModal(id);
        })
    },

    /**
     * 关闭模态框
     */
    hideModal : function(id){
        $("#" + id).hide();
        $(".modal-main").removeClass("animated fadeInDown");
        $(".modal-ms").removeClass("animated fadeInDown");
        $(".modal-test").removeClass("animated fadeInDown");
    },

    /**
     * 公用Confirm
     * @param {Object} tipMsg
     * @param {Object} callback
     */
    confirm : function(tipMsg,callback)
    {
        if($("alert-layer").length > 0)
        {
            return;
        }

        var html = '';

        html += '<div class="alert-layer">';
        html += '<div class="alert">';
        html += '<div class="alert-header">';
        html += '<h2>提示</h2>';
        html += '<span></span>';
        html += '</div>';
        html += '<div class="alert-con">';
        html += '<h3>'+tipMsg+'</h3>';
        html += '<div class="alert-btn">';
        html += '<a class="btn btn-white">取消</a>';
        html += '<a class="btn btn-default ripple">确定</a>';
        html += '</div></div></div>';
        html += '</div>';

        $("body").append(html);
        $(".alert").show();

        $(".alert-header span").on("click",function(){
            $(".alert-layer").remove();
        })

        $(".alert-btn a").eq(0).on("click",function(){
            $(".alert-layer").remove();
        })

        $(".alert-btn a").eq(1).on("click",function(){
            $(".alert-layer").remove();
            callback();
        })
    },


    /**
     * 加载动画
     */
    showLoading : function()
    {
        var html = '';
        html += '<div class="loading">';
        html += '<div class="loading-con">';
        html += '<img src="build/images/public/loadingImg.png" />';
        html += '<p>努力加载中...</p>';
        html += '</div>';
        html += '</div>';

        $("body").append(html);
        $(".loading").fadeIn();
    },

    /**
     * 结束加载动画
     */
    hideLoading : function()
    {
        $(".loading").remove();
        $(".container").fadeIn();
    },
}
