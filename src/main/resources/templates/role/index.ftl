<#include "../base/importbase.ftl"/>
<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">List of Roles</h2>
			</nav>
			<a type="button" class="btn btn-primary" href="/role/create">Add a new role</a>
		</div>
	</div>
	<div class="row pt-4">
		<div class="col-12">
			<table class="table table-striped">
			    <thead>
			        <tr>
			            <th scope="col">Role</th>
			        </tr>
			    </thead>
			        <tbody>

			 <#list items as role>
					<tr>
			            <td>${role.name}</td>
					</tr>
			 </#list>

			     </tbody>
			</table>
		</div>
	</div>
</div>