<#include "../base/importbase.ftl"/>

<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">Add a Role</h2>
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<form method="POST">
				<div class="form-group row">
					<div class="col-2">
						<label for="name">Role</label>
					</div>
					<div class="col-10">
						<input class="form-control " type="text" id="name" name="name" placeholder="Buyer"/>
					</div>
				</div>
				<input type="submit" class="btn btn-primary" value="Add role"/>
			</form>
		</div>
	</div>
</div>