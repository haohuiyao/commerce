var slide = function(isUse) {
	 $(".line-scroll-wrapper").width($(".line-wrapper").width() + $(".line-btn-delete").width());
    // 设定常规信息区域宽度=屏幕宽度
    $(".line-normal-wrapper").width($(".line-wrapper").width());
    // 设定文字部分宽度（为了实现文字过长时在末尾显示...）
//  $(".line-normal-msg").width($(".line-normal-wrapper").width() - 280);
	// 设定文字部分宽度（为了实现文字过长时在末尾显示...）
//	dom5.width(dom3.width() - 280);

	// 获取所有行，对每一行设置监听
	var lines = $(".line-normal-wrapper");
	var len = lines.length;
	var lastX, lastXForMobile;

	// 用于记录被按下的对象
	var pressedObj; // 当前左滑的对象
	var lastLeftObj; // 上一个左滑的对象

	// 用于记录按下的点
	var start;
	
//	if(!isUse)
//	{
//		alert(12345)
//		for(var i = 0; i < len; ++i)
//		{
//			lines[i].removeEventListener();
//			lines[i].removeEventListener('touchstart',function(e){
//				alert(1)
//			})
//			
//			lines[i].removeEventListener('touchmove',function(e){
//				
//			})
//			
//			lines[i].removeEventListener('touchend',function(e){
//				
//			})
//		}
//		
//		return;
//	}
	
	// 网页在移动端运行时的监听
	for(var i = 0; i < len; ++i) {
		lines[i].addEventListener('touchstart', function(e) {
			
			lastXForMobile = e.changedTouches[0].pageX;
			pressedObj = this; // 记录被按下的对象 

			// 记录开始按下时的点
			var touches = event.touches[0];
			start = {
				x: touches.pageX, // 横坐标
				y: touches.pageY // 纵坐标
			};
		});

		lines[i].addEventListener('touchmove', function(e) {
			// 计算划动过程中x和y的变化量
			var touches = event.touches[0];
			delta = {
				x: touches.pageX - start.x,
				y: touches.pageY - start.y
			};

			// 横向位移大于纵向位移，阻止纵向滚动
			if(Math.abs(delta.x) >= 10) {
				event.preventDefault();
			}
		});

		lines[i].addEventListener('touchend', function(e) {
			if(lastLeftObj && pressedObj != lastLeftObj) { // 点击除当前左滑对象之外的任意其他位置
				$(lastLeftObj).animate({
					marginLeft: "0"
				}, 300); // 右滑
				lastLeftObj = null; // 清空上一个左滑的对象
			}
			var diffX = e.changedTouches[0].pageX - lastXForMobile;
			
			if(diffX < -25) {
				$(pressedObj).animate({
					"marginLeft": "-60px"
				}, 300); // 左滑
				lastLeftObj && lastLeftObj != pressedObj &&
					$(lastLeftObj).animate({
						marginLeft: "0"
					}, 300); // 已经左滑状态的按钮右滑
				$(".line-btn-delete button").show() //先让删除键隐藏,在显示,不然加载有bug

				$(".list .line-btn-delete").on('click', function() {
					//alert($(this).parent().parent().index())
					var index1 = $(this).parent().parent().index() //获取index   非常重要
					$(".line-wrapper").eq(index1).css({"display": "none"})
				})

				lastLeftObj = pressedObj; // 记录上一个左滑的对象
			} else if(diffX > 25) {
				if(pressedObj == lastLeftObj) {
					$(pressedObj).animate({
						marginLeft: "0"
					}, 300); // 右滑
					lastLeftObj = null; // 清空上一个左滑的对象
				}
			}
		});
	}

}