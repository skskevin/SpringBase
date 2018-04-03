<div class="row">
	<div class="col-md-12">
		<form id="roleEditForm">
			<input type="hidden" id="id" name="id" value=${bean.id}>
			<div class="box-body">
				<div class="form-group">
					<label id="roleNameLabel">角色名</label>
					<input type="text" class="form-control" name="roleName" id="roleName" value=${bean.roleName} placeholder="角色名...">
				</div>
				<div class="form-group">
					<label id="roleValueLabel">角色值</label>
					<input type="text" class="form-control" name="roleValue" id="roleValue" value=${bean.roleValue} placeholder="角色值...">
				</div>
				<div class="form-group">
					<label>权限：</label>
					<label>
						<input type="checkbox" id="allCheckbox" class="flat-red" onClick="onClickCheckbox('allCheckbox','permission')">全选
					</label>
					<br/>
					<#list permissions as permission>
						<#if bean.permissionList??>
							<label>
			                  <input type="checkbox" name="permission" class="flat-red" value="${permission.id}"<#list bean.permissionList as beanPermission> <#if beanPermission.permissionsValue == permission.permissionsValue>checked</#if></#list>> ${permission.permissionsName}
			                </label>
						<#else>
							<label>
			                  <input type="checkbox" name="permission" class="flat-red" value="${permission.id}"> ${permission.permissionName}
			                </label>
						</#if>
					</#list>
				</div>
             </div>
			<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
               		<button onclick="roleUpdate();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新</button>
				</div>
          	</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	
</script>