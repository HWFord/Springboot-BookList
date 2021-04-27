<#include "../base/importbase.ftl"/>

<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">Add a User</h2>
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-12">

			<form method="POST">
				<div class="form-group row">
					<div class="col-12">
						<label for="firstname">First Name</label>
					</div>
					<div class="col-12">
						<input class="form-control " type="text" id="firstname" name="firstname" placeholder="JK"/>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-12">
						<label for="lastname">Last Name</label>
					</div>
					<div class="col-12">
						<input class="form-control" type="text" id="lastname" name="lastname" placeholder="Rowling"/>
					</div>
				</div>
				<div class="form-group row">
					<#include "./selectRole.ftl">
			  	</div>
			  	<div class="form-group row">
					<#include "./selectBooks.ftl">
			  	</div>
			  	<div class="row text-right">
			  		<div class="col-12">
						<input type="submit" class="btn btn-primary" value="Add user"/>
					</div>
				</div>
			</form>

		</div>
	</div>
</div>