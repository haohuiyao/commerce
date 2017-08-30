<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script language="javascript" charset="utf-8">
Ext.onReady(function(){
	  Ext.state.Manager.setProvider(new Ext.state.SessionStorageStateProvider({}));
	  var grid1 = new Ext.grid.TableGrid1("${currentGridId}",{remove:false});
	  $(".x-grid-panel").css("width","100%");
	  $(".x-panel-body-noheader").css("width","100%");
	  $(".x-grid3").css("width","100%");
	  $(".x-grid3-header").css("width","100%");
	  $(".x-grid3-scroller").css("width","100%");
	  $(".x-grid3-header-inner").css("width","100%");
	  $(".x-grid3-scroller").css("height","auto");
		$(".x-grid3-scroller").css("padding-bottom","10px");
		$(".x-grid3").css("height","auto");
		$(".x-grid3-header-inner").css("height","auto");
		$(".x-panel-body-noheader").css("height","auto");
		$(".x-grid-panel").css("height","auto");
	});
</script>
