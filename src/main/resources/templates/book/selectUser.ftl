<div class="col-2">
	<label for="userId">User</label>
</div>
<div class="col-12">
	<select name="userId" class="form-control">
			<option selected disabled>No user</option>
		<#list users as key, value>
			<option value="${key}">${value}</option>
		</#list>
	</select>
</div>