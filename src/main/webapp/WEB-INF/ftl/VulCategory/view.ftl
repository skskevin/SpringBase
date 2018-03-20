<div class="row">
	<div class="col-md-12">
		<div class="box-body  no-padding">
			<table class="table table-striped">
                <tr>
                	<td>名称：</td>
                	<td style="width: 90%">${result.name}</td>
                </tr>
                <tr>
                	<td>创建时间：</td>
                	<td>${result.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                </tr>
         	</table>
         	<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
				</div>
			</div>
    	</div>
	</div>
</div>