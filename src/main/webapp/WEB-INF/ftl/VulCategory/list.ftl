<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<h3 class="box-title">漏洞分类管理</h3>
				<div class="box-tools pull-right">
					<#-- <@shiro.hasPermission name="super:insert"> -->
						<a  class="btn btn-sm btn-primary" target="modal" modal="lg" href="${ctx}/VulCategory/add">添加</a>
					<#-- </@shiro.hasPermission> -->
				</div>
			</div>
			<div class="box-body">
				<div class="clearfix">
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span>
							<input type="text" class="form-control" name="name" placeholder="根据名称搜索...">
						</div>
					</div>
					<div class="col-md-4">
						<button type="button" onclick="dataReload();" class="btn btn-primary" id="btn-search">搜索</button>
					</div>
				</div>
				<table id="myDatatable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<tr>
								<th>序号</th>
								<th>名称</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var myDatatable;
$(function() {
	//初始化表格
	var No=0;
	myDatatable = $('#myDatatable').DataTable({
		"dom":'itflp',
		"processing":true,
		"searching":false,
		"lengthChange": true,
        //"pageLength": 10,
        "pagingType": "full_numbers",
        "language":{
            "emptyTable": "暂无数据",
            "lengthMenu": "每页显示 _MENU_ 条",
            "search": "搜索：",
            "info": "共 <b>_TOTAL_</b> 条，显示: [<b>_START_</b> -- <b>_END_</b>] 条",
            "infoFiltered": "(从_MAX_条记录中查询)",
            "paginate": {
                "first":      "首页",
                "last":       "尾页",
                "next":       "下一页",
                "previous":   "上一页"
            },
        },
		"serverSide":true, //启用服务器端分页
		"bInfo":false,
		//"language":{"url":"plugins/datatables/language.json"},
		"ajax":{"url":"${ctx}/VulCategory/page","type":"post"},
		"columns":[ 
		    {"data":null}, 
			{"data":"name"},
			{"data":"createTime"},
			{"data":null} 
			],
		"columnDefs" : [
			{
			    targets: 0,
			    data: null,
			    render: function (data) {
			    	No=No+1;
			        return No;
			    }
			},
            {
				"targets" : -1,
				"data" : null,
				"render" : function(data) {
					var btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="${ctx}/VulCategory/view?id='+ data.id+ '">查看</a> &nbsp;';
						if(data.roleValue != 'super'){
							btn = btn+''
							+'<a class="btn btn-xs btn-info"  target="modal" modal="lg" href="${ctx}/VulCategory/edit?id='+ data.id+'">修改</a> &nbsp;'
							+'<a class="btn btn-xs btn-default" callback="dataReload();" data-body="确认要删除吗？" target="ajaxTodo" href="${ctx}/VulCategory/delete?id='+ data.id + '">删除</a>'
							+'';
						}
				return btn;
			}
		} ]
	}).on('preXhr.dt', function ( e, settings, data ) {
	//console.log(data,settings)
		No=0;
    } );
});

var list_ajax;
var date_ajax;
var search_ajax;
function dataReload(){
	var name = $("input[name='name']").val();
	var param = {
		"search" : name
	};
	myDatatable.settings()[0].ajax.data = param;
	myDatatable.ajax.reload();
}

<#-- 此函数必须放在list.ftl中 -->
function update(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#name").val()==""){
		$("#nameLabel").prepend('<span class="errorClass" style="color:red">*名称不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		editAjaxPost();
	}
}
	
function editAjaxPost() {
	var options = {
        url: '${ctx}/VulCategory/update',
        type: 'post',
        dataType: 'text',
        data: $("#EditForm").serialize(),
        success: function (data) {
        	$("#lgModal").modal('hide');
        	alertMsg("更新成功","success");
        	dataReload();
        }
	};
	$.ajax(options);
}

//回车事件绑定搜索按钮
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==13){ 
		$('#btn-search').click();
	}
}; 
</script>