/**
 * =========================================================================================================================
 * 
 * {common} 阿里云上传图片
 * {author} liang
 * =========================================================================================================================
 */

var Oss = 
{
	client : null,
	region : ProjectKey.OSSKEY.REGION,
	accessKeyId : ProjectKey.OSSKEY.ACCESS_KEY_ID,
	accessKeySecret : ProjectKey.OSSKEY.ACCESS_KEY_SECRET,
	bucket : ProjectKey.OSSKEY.PUBLIC_BUCKET,
	
	/*
     * 阿里云存储初始化
     */
	initOssKey : function()
	{
		var self = this;
		
		self.client = new OSS.Wrapper({
			region : self.region,
		    accessKeyId : self.accessKeyId,
		    accessKeySecret : self.accessKeySecret,
		    bucket : self.bucket
		})
	},
	
	/*
	 * 上传
	 */
	uploadFile : function(objKey,file,success)
	{
		var self = this;
		
		this.client.multipartUpload(objKey, file,{
	      	progress : self.progress,
	    }).then(function (result) {
	    	var FixResult = result;
	    	FixResult.fixUrl = self.imgUrlPrefix(objKey);
	    	success && success(FixResult);
		}).catch(function (err) {
			alert("上传出错");
	    });
	},
	
	progress : function(p)
	{
		return function (done) 
		{
		    //if(p == 1 )
		    //{
		    	done();
		    //}
		}
	},
	
	/*
	 * 拼接url地址
	 */
	imgUrlPrefix : function(ossKey)
	{
		var self = this;
	  	var imgUrl = "http://" + self.bucket + "." + self.region + ".aliyuncs.com/" + ossKey + '?x-oss-process=image/format,jpg,resize,p_50';
		return imgUrl;
	}
	
}



			
			
