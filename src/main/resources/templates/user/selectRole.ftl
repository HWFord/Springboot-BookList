<div class="col-12">
	<label for="roleId">Role</label>

</div>
<div class="col-12">
	<select name="roleId" class="form-control">
		<#list roles as key, value>
			<option value="${key}">${value}</option>
		</#list>
	</select>
	<small class="text-danger">A user must have a role</small>
</div>