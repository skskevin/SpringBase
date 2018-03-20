<div class="row">
	<div class="col-md-12">
		<form id="EditForm">
			<input type="hidden" id="id" name="id" value=${result.id}>
			<div class="box-body">
				<div class="form-group">
					<label id="nameLabel">名称</label>
					<input type="text" class="form-control" name="name" id="name" value=${result.name} placeholder="类别名称...">
				</div>
             </div>
			<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
               		<button onclick="update();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新</button>
				</div>
          	</div>
		</form>
	</div>
</div>
<script type="text/javascript">
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
		    console.log(11);
			ajaxPost();
		}
	}
		
	function ajaxPost() {
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
</script>