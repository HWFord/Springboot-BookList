<#include "../base/importbase.ftl"/>

<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">List of Users</h2>
			</nav>
			<a type="button" class="btn btn-primary" href="/user/create">Add a new user</a>
		</div>
	</div>
	<div class="row pt-4">
		<div class="col-12">

			 <table class="table table-striped">
			    <thead>
			        <tr>
			            <th scope="col">Firstname</th>
			            <th scope="col">Lastname</th>
			            <th scope="col"><a href="/role">Role</a></th>
			            <th scope="col"><a href="/book">Books</a></th>
			            <th scope="col">Select a user</th>
			        </tr>
			    </thead>
			        <tbody>

			 <#list items as user>
					<tr>
			            <td>${user.firstname}</td>
				    	<td>${user.lastname}</td>
				    	<td>${user.getRole().getName()}</td>
				    	<td>
					    	<ul class="list-group">
						    	<#list user.getBooks() as book>
								  <li class="list-item d-flex justify-content-between align-items-center">
								  	${book.getName()}
								  	<span class="badge badge-primary badge-pill">${book.getPrice()}$</span>
								  </li>
								</#list>
							</ul>
				    	<td>
					        <a href="/process/login/${user.id}" class="btn btn-outline-success btn-sm"/>Connect with this user</a>
					    </td>
					</tr>
			 </#list>

			    </tbody>
			</table>
		</div>
	</div>
</div>