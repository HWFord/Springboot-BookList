<#include "../base/importbase.ftl"/>
<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">Welcome Customer <span class="text-success">${person.getFirstname()} ${person.getLastname()}</span></h2>
				<a type="button" class="btn btn-primary" href="/user/details/${person.getId()}">See Customer details</a>
			</nav>
		</div>
		<div class="col-12">
			<h3 class="navbar-brand">Filter books</h3>
			<a type="button" class="btn btn-primary" href="/process/login/${person.getId()}/nameSearch">By name</a>
			<a type="button" class="btn btn-primary" href="/process/login/${person.getId()}/nbpagesSearch">By number of pages</a>
			<a type="button" class="btn btn-primary" href="/process/login/${person.getId()}/priceSearch">By price</a>
		</div>

	</div>
	<div class="pt-4">
		<div class="row">
			<div class="card-group">
				<#list books as book>
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
					      	<a href="/process/buy/${person.id}+${book.getId()}" class="btn btn-outline-secondary btn-sm"/>Buy this book</a>
					    </div>

					</div>
					</div>
				</#list>
			</div>
		</div>
	</div>
</div>