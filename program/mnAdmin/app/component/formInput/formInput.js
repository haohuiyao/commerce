/**
 * Created by Administrator on 2017/7/17.
 * form 输入框组件
 * formObj : { formDesc : "旧密码",formType : "password",formDisabled : false}
 * formModel : 数据绑定
 */

app.directive('formInput',function(){
    return{
        restrict : 'AE',
        templateUrl : 'app/component/formInput/formInput.html',
        replace : true,
        scope : {
            formObj : "=formObj",
            formModel : "=formModel"
        },
        link : function(scope, element, attrs){
            scope.$watch('formObj.formDisabled',function(value){
                if(value)
                    element.find('input').attr('disabled','disabled');
                else
                    element.find('input').removeAttr("disabled");

            })
        }
    }
})