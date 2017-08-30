
/**
 * created by xiezl 2017-07-07
 * 
 * 给Function 添加额外方法
 * return this; 是为了链式添加方法
 * 使用方法比如说：
 * var obj = new Function();
 * obj.addMethods("checkEmial",function(){
 * 	
 * }).addMethods("checkName",function(){
 * 	
 * }).......;
 * 该方法是为了避免污染了原生对象Function
 * 
 */

Function.prototype.addMethod = function(name , fn)
{
	this[name] = fn;
	return this;
}

/*
 * 创建 Function对象
 * */
var fn = new Function();

/*
 * 创建 封闭区间
 * */
(function(fn){
	
	fn.addMethod("isEmpty",function(strVal){
		/// <summary>判断一个字符串是否为空</summary>     
	   /// <param name="strVal" type="String">需要判断的字符串</param>   
		var isEmpty = false;
		try
		{
			if(strVal == "" || strVal == "''" || strVal == ''
				|| strVal == "null" || strVal == "{}"
				|| strVal == "[]" || strVal == null 
				|| strVal == "'[]'" || strVal == "<null>"
				|| strVal == undefined || strVal == NaN )
			{
				isEmpty = true;
			}
			else
			{
				isEmpty = false;
			}
		}
		catch(e)
		{
			console.log(e);
		}
		return isEmpty;
		
	}).addMethod("deepCopy",function(source){
		var result;
		try
		{
			(source instanceof Array) ? (result = []) : (result = {});
		
			for (var key in source) 
			{
				result[key] = (typeof source[key]==='object') ? deepCopy(source[key]) : source[key];
			}    
		}
		catch(e)
		{
			console.log(e);	
		}
		
		return result;
		
	}).addMethod("Extends",function(){
		//原型继承方法
		var F = function(){}, args = arguments , i = 0 , len = args.length ;
		
		try
		{
			for(; i < len ; i ++)
			{
				for(var j in args[i])
				{
					F.prototype[j] = args[i][j];
				}
			}
		}
		catch(e)
		{
			console.log(e);
		}
		
		return new F();
		
	}).addMethod("setLocalStorage",function(key,value){
		/**
		 * @本地存储@
		 * */
		localStorage.setItem(key,value);
		
	}).addMethod("getLocalStorage",function(key){
		/**
		 * @获取本地存储@
		 * */
		return localStorage.getItem(key);
		
	}).addMethod("removeLocalStorageBykey",function(key){
		/**
		 * @销毁本地存储@
		 * */
		localStorage.removeItem(key);
		
	}).addMethod("combine",function(){
		/**
		 * 字符串合并
		 * arguments表示参数
		 * */
		var args = arguments , retStr = this.EMPTY;
		
		if(args.length == 0)
		{
			return this.EMPTY;
		}
		
		for(var i = 0 ; i < args.length ; i ++)
		{
			retStr += args[i].toString();
		}
		
		return retStr;
		
	}).addMethod("numberPrefix",function(size,num){
		/// <summary>数字修改</summary>     
	  	/// <param name="size" type="String">修改后的位数 如 2 num 传的是1 ， 返回是01</param>
	  	/// <param name="num" type="String">需要修改的数</param>
		if(this.isEmpty(num))
		{
			return num;
		}
		
		if(num.toString().length >= size)
		{
			return num;
		}
		
		var preZero = (new Array(size)).join('0');
		
		return preZero.substring(0, size - num.toString().length ) + num;
		
	}).addMethod("formatDate",function(timestamp,onlyDate){
		/**
		 * @时间戳转时间@
		 *  @onlyDate true表示仅显示日期 false表示显示日期和时间@
		 * */
		if(timestamp == 0 || timestamp == undefined)
		{
			console.log("时间戳格式不对");
			return 0;
		}
		
		var date = null;
		
		if(timestamp.toString().length > 10)
		{
			date = new Date(timestamp);
		}
		else
		{
			date = new Date(timestamp * 1000);
		}
		
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var day = date.getDate();
	    var hour = date.getHours();
	    var minute = date.getMinutes();
	    var second = date.getSeconds();
		
		if (onlyDate)
	    {
	        return this.numberPrefix(4, year) + "-" + this.numberPrefix(2, month) + "-" + this.numberPrefix(2, day);
	    }
	
	    return this.numberPrefix(4, year) + "-" + this.numberPrefix(2, month) + "-" + this.numberPrefix(2, day) + " "
	        + this.numberPrefix(2, hour) + ":" + this.numberPrefix(2, minute) + ':' + this.numberPrefix(2, second);
		
	}).addMethod("convertDateToTimestamp",function(dateStr){
		/**
		 * @时间转时间戳@
		 * */
		if(isEmpty(dateStr))
		{
			return "";
		}
		
		return (Date.parse(new Date(dateStr))) / 1000;
		
	}).addMethod("getAgeByBirthday",function(strBirthday){
		/**
		 * @根据出生日期获取年龄@
		 * */
		
		if(strBirthday == "" || strBirthday == undefined || strBirthday == null)
		{
			console.log("日期传错了");
			return ;
		}
		
		var returnAge;
		var strBirthdayArr=strBirthday.split("-");
		var birthYear = strBirthdayArr[0];
		var birthMonth = strBirthdayArr[1];
		var birthDay = strBirthdayArr[2];

		d = new Date();
		var nowYear = d.getFullYear();
		var nowMonth = d.getMonth() + 1;
		var nowDay = d.getDate();

		if(nowYear == birthYear)
		{
			returnAge = 0;//同年 则为0岁
		}
		else
		{
			var ageDiff = nowYear - birthYear ; //年之差
			if(ageDiff > 0){
				if(nowMonth == birthMonth) {
					var dayDiff = nowDay - birthDay;//日之差
					if(dayDiff < 0)
					{
						returnAge = ageDiff - 1;
					}
					else
					{
						returnAge = ageDiff ;
					}
				}
				else
				{
					var monthDiff = nowMonth - birthMonth;//月之差
					if(monthDiff < 0)
					{
						returnAge = ageDiff - 1;
					}
					else
					{
						returnAge = ageDiff ;
					}
				}
			}
			else
			{
				returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
			}
		}

		return returnAge;//返回周岁年龄
		
	}).addMethod("Base64",function(){
		/*
		 *Base64 加密对象方法
		 * */
		
	 	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 	
 		/*
 		 *Base64 公共方法 调用方式是  
 		 * var base = new fn.Base64();
 		 * base.encode(param);//加密
 		 * base.decode(param);//解密
 		 * 
 		 * */
 		this.encode = function (input) 
 		{
			var output = "";
			var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
			var i = 0;
			input = _utf8_encode(input);
			while (i < input.length) 
			{
				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);
				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;
				if (isNaN(chr2)) 
				{
				   	enc3 = enc4 = 64;
				} 
				else if (isNaN(chr3))
				{
				    enc4 = 64;
				}
				output = output +
				_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
				_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
			}
			
			return output;
		}
 		
 		// public method for decoding
		this.decode = function(input)
		{
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
			while (i < input.length)
			{
				enc1 = _keyStr.indexOf(input.charAt(i++));
				enc2 = _keyStr.indexOf(input.charAt(i++));
				enc3 = _keyStr.indexOf(input.charAt(i++));
				enc4 = _keyStr.indexOf(input.charAt(i++));
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
				output = output + String.fromCharCode(chr1);
				if (enc3 != 64) 
				{
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64)
				{
					output = output + String.fromCharCode(chr3);
				}
			}
			
			output = _utf8_decode(output);
			return output;
		}
 		
 		// private method for UTF-8 encoding
		_utf8_encode = function(string)
		{
			string = string.replace(/\r\n/g, "\n");
			var utftext = "";
			for (var n = 0; n < string.length; n++) 
			{
				var c = string.charCodeAt(n);
				if (c < 128)
				{
					utftext += String.fromCharCode(c);
				} 
				else if ((c > 127) && (c < 2048))
				{
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				} 
				else 
				{
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}
			}
			
			return utftext;
		}
		
		// private method for UTF-8 decoding
		_utf8_decode = function(utftext)
		{
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;
			while (i < utftext.length)
			{
				c = utftext.charCodeAt(i);
				if (c < 128)
				{
					string += String.fromCharCode(c);
					i++;
				}
				else if ((c > 191) && (c < 224)) 
				{
					c2 = utftext.charCodeAt(i + 1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				}
				else
				{
					c2 = utftext.charCodeAt(i + 1);
					c3 = utftext.charCodeAt(i + 2);
					string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}
			}
			
			return string;
		}
 		
	}).addMethod("Throttle",function(){
		
		/**
		 *节流 类
		 * 节流器
		 * 
		 * 节流器，是为了屏蔽重复的事情（业务逻辑）只执行最后一次。比如我们做的回到顶部按钮，我们会监听scroll事件，
		 * 在这个时候我们没滚动一下滚动条就会触发scroll事件，这个时候我们就需要节流。优化体验
		 * 调用方法是
		 */
		
		var isClear = arguments[0], fn;
		
		//如果第一个参数是boolean类型，那么第一个参数则表示是否清楚定时器
		if(typeof isClear === 'boolean')
		{
			//第二个参数则为函数
			fn = arguments[1];
			//函数的计时器句柄存在的话，则清楚该定时器
			fn._throttleID && clearTimeout(fn._throttleID);
		}
		else
		{
			//第一个参数为函数
			fn = isClear,
			//第二个为函数执行时的参数
			param = arguments[1];
			
			//对执行时的参数适配默认值
			var p = extend({
				context : null,
				args : [],
				time : 300
			},param);
			
			fn._throttleID = setTimeout(function(){
				fn.apply(p.context , p.args);
				
			},p.time);
		}
		
	}).addMethod('formNoEmpty',function(dataArr){
		/**
		 * 判断表单信息是否为空
		 * @param {Object} dataArr
		 * dataArr : [{data:"表单数据",errDesc:"提示信息"},{data:"表单数据",errDesc:"提示信息"}]
		 */
		var bool = true;
		for(var i = 0;i < dataArr.length;i++){
			if(dataArr[i].data == ""||dataArr[i].data == null){
				common.alert(dataArr[i].errDesc);
				bool = false;
				break;
			}
		}
		return bool;
	}).addMethod('isSame', function (dataArr) {
		var bool = true;
		for(var i = 0; i < dataArr.length; i++){
			if(dataArr[0] != dataArr[i]){
				bool = false;
				break;
			}
		}
		return bool;
	}).addMethod('jsonParse', function (str) {
		var obj = {};
		try {
			obj = JSON.parse(str);
		} catch (e) {
			obj = {};
		}
		return obj;
	});
	
/*
 * 针对项目做相应的添加
 */

})(fn);

/**
 * 定义Function 常量
 */
Function.prototype.EMPTY = "";
