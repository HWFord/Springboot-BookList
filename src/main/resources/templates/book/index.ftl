<#include "../base/importbase.ftl"/>


<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">List of books</h2>
			</nav>
		</div>
		<div class="col-12">
				<a type="button" class="btn btn-primary" href="/book/create">Add book</a>
		</div>

	</div>

	<div class="pt-4">
		<div class="row">
			<div class="card-group">
				<#list items as book>
				<div class="col-3 py-2 pb-4">
					<div class="card ">
					  <img class="card-img-top" src="/images/book.png" alt="Book icon">
					  <div class="card-body">
					    <h5 class="card-title">${book.name}</h5>
					    <p class="card-text">${book.price} $</p>
					    <p class="card-text"><small class="text-muted">${book.nbpages} pages</small></p>
					  </div>
						<div class="card-footer">
							<p class="text-muted">Current owner:</p>
					      	<p class="text-muted">
					      	<#if book.user??>
							  ${book.user.getFirstname()} ${book.user.getLastname()}
							<#else>
							  N/A
							</#if>
					      	</p>
					    </div>

					</div>
					</div>
				</#list>
			</div>
		</div>
	</div>
</div>