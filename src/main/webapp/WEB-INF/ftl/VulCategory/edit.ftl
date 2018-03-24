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
