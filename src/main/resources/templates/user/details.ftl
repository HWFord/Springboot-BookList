<#include "../base/importbase.ftl"/>
<div class="container">
	<div class="row">
		<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			  		<a href="/process/login/${item.id}" class="breadcrumb-item "/>Back to shop</a>
			  </ol>
		</nav>
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">User details for <span class="text-success font-weight-bold">${item.getFirstname()}</span></h2>
			</nav>
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
			            <th scope="col"><a href="/book">Books owned</a></th>
			        </tr>
			    </thead>
			        <tbody>
					<tr>
			            <td>${item.firstname}</td>
				    	<td>${item.lastname}</td>
				    	<td>${item.getRole().getName()}</td>
				    	<td>
					    	<ul class="list-group">
						    	<#list item.getBooks() as book>
								  <li class="list-item d-flex justify-content-between align-items-center">
								  	${book.getName()}
								  </li>
								</#list>
							</ul>
						</td>
					</tr>
			    </tbody>
			</table>
		</div>
	</div>
</div>