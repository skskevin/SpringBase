<div class="row">
	<div class="col-md-12">
		<form id="AddForm">
				<div class="modal-body">
					<div class="form-group">
						<label id="NameLabel">角色</label>
						<input type="text" class="form-control" name="name" id="name" placeholder="输入类别名称...">
					</div>
					
				</div>
				<div class="modal-footer">
					<div class="pull-right">
						<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>取消</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="roleAdd();"><i class="fa fa-save"></i>保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function dataReload(){
	var name = $("input[name='name']").val();
	var param = {
		"search" : name
	};
	myDatatable.settings()[0].ajax.data = param;
	myDatatable.ajax.reload();
}

function roleAdd(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#name").val()==""){
		$("#NameLabel").prepend('<span class="errorClass" style="color:red">*类别名不能为空</span><br class="errorClass"/>');
		status = 0;
	}

	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '${ctx}/VulCategory/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#AddForm").serialize(),
	        success: function (data) {
	        	debugger;
	        	$("#lgModal").modal('hide');
	        	alertMsg("添加成功","success");
	        	dataReload();
	        }
		})
	}
}
</script>