<div class="row">
	<div class="col-md-12">
		<form id="permissionEditForm">
			<input type="hidden" id="id" name="id" value=${bean.id}>
			<div class="box-body">
				<div class="form-group">
					<label for="exampleInput" id="permissionNameLabel">权限名</label>
					<input type="text" class="form-control" name="permissionsName" id="permissionsName" value=${bean.permissionsName!} placeholder="输入权限名...">
				</div>
				<div class="form-group">
					<label for="exampleInput" id="permissionValueLabel">权限值</label>
					<input type="text" class="form-control" name="permissionsValue" id="permissionsValue" value=${bean.permissionsValue!} placeholder="输入权限值...">
				</div>
             </div>
			<div class="box-footer">
				<div class="pull-right">
               		<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
               		<button type="button" class="btn btn-primary btn-sm" onclick="permissionUpdate();"><i class="fa fa-paste"></i>更新</button>
				</div>
          	</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	
</script>