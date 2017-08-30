

/*
 * 选择
 */

var CommonSelect = {
	
	
	/**
     * arr:列表数组
     * id：点击单选的id
     * oneSelected ： 判断是否选中的key值
     * @param {Object} arr
     * @param {Object} oneSelected
     */
	selectItem : function(arr,idKey,idValue,oneSelected,isOneSel){
		
		for(var i = 0 ; i < arr.length ; i ++)
		{
			if(isOneSel)
			{
				arr[i][oneSelected] = false;
			}
			if(arr[i][idKey] == idValue)
			{
				arr[i][oneSelected] = !arr[i][oneSelected];
			}
		}
		
		return arr;
	},
	
	/*
	 * 找出选择项
	 */
	findHasSelItem : function(arr,oneSelected){
		var obj = {};
		for(var i = 0 ; i < arr.length ; i ++)
		{
			if(arr[i][oneSelected])
			{
				obj = arr[i];
			}
		}
		
		return obj;
	}
}
