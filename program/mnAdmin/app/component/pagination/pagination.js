/**
 * =========================================================================================================================
 * {demo} 
 * {directive name} 分页
 * {author} liang
 * =========================================================================================================================
 */

app.directive('myPagination', function() {
    return { 
        restrict : 'AE', 
        templateUrl : 'app/component/pagination/pagination.html',
        replace : true,
        scope : {
        	option: '=pageOption'
        },
        link : function($scope, element, attrs)
        {
        	if (!$scope.option) 
        	{
				$scope.option = 
				{
					curr: 1,
					all: 1,
					count: 1,
					click: function(page) {
						
					}
				}
			}

			$scope.$watch("option.all", function(data) {
				$scope.page = getRange($scope.option.curr, $scope.option.all, $scope.option.count);
			});

			//绑定点击事件
			$scope.pageClick = function(page) {
				if (page == 'last') 
				{
					page = parseInt($scope.option.curr) - 1;
				} 
				else if (page == 'next') 
				{
					page = parseInt($scope.option.curr) + 1;
				}
				
				if (page < 1) 
				{
					page = 1;
				}
				else if (page > $scope.option.all)
				{
					page = $scope.option.all;
				} 
				//点击相同的页数 不执行点击事件
				if (page == $scope.option.curr) 
				{
					return;
				}
				if ($scope.option.click && typeof $scope.option.click === 'function') 
				{
					$scope.option.click(page);
					$scope.option.curr = page;
					$scope.page = getRange($scope.option.curr, $scope.option.all, $scope.option.count);
				}
			};

			$scope.pageFirst = function() {
				$scope.option.curr = 1;
				$scope.page = getRange($scope.option.curr, $scope.option.all, $scope.option.count);
				$scope.option.click($scope.option.curr);
			};

			$scope.pageLast = function() {
				$scope.option.curr = $scope.option.all;
				$scope.page = getRange($scope.option.curr, $scope.option.all, $scope.option.count);
				$scope.option.click($scope.option.curr);
			};

			//返回页数范围（用来遍历）

			function getRange(curr, all, count) 
			{
				//计算显示的页数
				curr = parseInt(curr);
				all = parseInt(all);
				count = parseInt(count);
				var from = curr - parseInt(count / 2);
				var to = curr + parseInt(count / 2) + (count % 2) - 1;
				//显示的页数容处理
				if (from <= 0) 
				{
					from = 1;
					to = from + count - 1;
					if (to > all) 
					{
						to = all;
					}
				}
				if (to > all) 
				{
					to = all;
					from = to - count + 1;
					if (from <= 0) 
					{
						from = 1;
					}
				}
				
				var range = [];
				
				for (var i = from; i <= to; i++) 
				{
					var itemObj = {};
					itemObj.value = i;
					itemObj.keys = i;
					range.push(itemObj);
				}
				
				var nObj = {};
				nObj.value = "下一页";
				nObj.keys = "next";
				
				var lObj = {};
				lObj.value = "上一页";
				lObj.keys = "last";
				
				range.push(nObj);
				range.unshift(lObj);
				return range;
			}
        	
        }
    }; 
}); 