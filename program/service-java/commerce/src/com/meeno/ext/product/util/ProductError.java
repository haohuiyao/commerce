package com.meeno.ext.product.util;

public interface ProductError {

	String ID_NULL = "id为空";

	// product category
	String CATEGORY_NOT_EXIST = "分类不存在";
	String CATEGORY_ID_IS_NULL = "分类ID为空";
	String CATEGORY_ORDER_CANT_BE_NULL = "分类位置不能为空";
	String CATEGORY_DO_NOT_HAS_PRE_ORDER = "分类原位置不存在";
	String CANT_CHANGE_ORDER_OF_THIS_CATEGORY = "此分类位置不允许修改";
	String CATEGORY_ORDER_ERR = "分类顺序异常";
	String CATEGORY_NEW_ORDER_OUT_OF_BOUNDS = "分类新位置越界";
	String CANT_CHANGE_ORDER_OF_THIS_CATEGORY_TO_FIRST = "禁止将此分类置于首位";
	String CANT_DELETE_THIS_CATEGORY = "此分类禁止删除";
	String CANT_DELETE_CATEGORY_WITH_GOODS = "分类下有商品，禁止删除";
	String DUPLICATED_CATEGORY_NAME = "分类名重复";
	String CANT_CHANGE_NAME_OF_THIS_CATEGORY = "不允许修改此分类名称";
	String CATEGORY_NAME_CANT_BE_EMPTY = "分类名称不能为空";
	String CATEGORY_IMGURL_CANT_BE_EMPTY = "分类图片不能为空";
	String FRANKING_TEMPLET_NOT_EXIST = "运费模板不存在";
	String CATEGORY_NAME_ERR="分类名称改变";
	
	String PARAMS_TEMPLET_NOT_EXIST = "参数模板不存在";
	String TEMPLATE_ID_IS_NULL = "参数模板ID为空";
	String TEMPLATE_NOT_EXIST = "参数模板不存在";
	String TEMPLATE_NOT_AVA = "参数模板不可用";
	String NEW_PARAM_IS_EMPTY = "新增参数为空";
	String TEMPLATE_NAME_CANT_BE_EMPTY = "参数模板名称不能为空";
	String TEMPLATE_CONTENT_CANT_BE_EMPTY = "参数模板内容不能为空";
	String DUPLICATED_NAME_TEMPLATE_EXIST = "有同名参数模板存在";
	String TEMPLATE_FORMAT_ERR = "参数模板格式有误";
	String TEMPLATE_PARAM_VALUE_FORMAT_ERR = "模板参数格式有误";
	String NEW_PARAM_FORMAT_ERR = "新模板参数格式有误";
	String PARAM_ALREADY_EXIST = "模板参数已存在";
	String BRAND_NOT_EXIST = "产品品牌不存在";

	String SOURCE_NOT_EXIST = "货源不存在";

	String PRODUCT_NOT_EXIST = "产品不存在";

	String SKU_NOT_EXIST = "SKU不存在";

	// others
	String NO_MODIFICATION = "未作任何修改";
	String AMOUNT_ERR = "数量有误";
	String OPERATOR_IS_NULL = "操作人为空";
	String TYPE_CANT_BE_NULL = "类型不能为空";
	String PRODUCT_ALREADY_PUTUP = "产品已经上架";
	String PRODUCT_ALREADY_PUTOUT = "产品已经下架";

	// productRel
	String PRODUCT_REL_REPEAT = "关联关系重复";
	String PRODUCT_REL_EXIST = "关联关系已存在";
	String PRODUCT_REL_ERROR = "关联关系错误";
	String PRODUCT_REL_FORMAT_ERROR = "关联关系格式错误";
	String PRODUCT_ID_NULL = "产品ID为空";
}
