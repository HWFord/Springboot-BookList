<#include "../base/importbase.ftl"/>
<div class="container">
	<div class="row">
		<div class="col-12">
			<nav class="navbar justify-content-between px-0">
			  <h2 class="navbar-brand">Advanced book search for Customer <span class="text-success">${person.getFirstname()} ${person.getLastname()}</span></h2>
				<a type="button" class="btn btn-primary" href="/process/login/${person.getId()}">Show all books for sale</a>
			</nav>
		</div>
		<div class="col-12">
			<h6>Search for books by price</h6>
			<form class="pt-2" method="POST">
				<div class="form-group">
					<div class="form-row">
						<div class="col-12 form-check form-check-inline">
							<div class="text-center pb-1">
						    		<input class="form-check-input" type="radio" id="priceSmaller" value="smaller" name="priceFilter" checked>
									<label class="form-check-label" for="priceSmaller">Equal to or smaller than</label>

						    		<input class="form-check-input" type="radio" id="priceBigger" value="bigger" name="priceFilter">
									<label class="form-check-label" for="priceBigger">Equal to or bigger than</label>
							</div>
						</div>
						<div class="col-12">
							<input id="titleSearch" name="price" type="number" step="0.01" class="form-control" type="search" placeholder="20.00" aria-label="Search">
							<small id="titleHelp" class="form-text text-muted">Enter a price and click the search button to bring up books with prices either equal to or smaller or equal to and bigger than your search</small>
							<#if errorMessage?has_content><p class="text-danger font-weight-bold">${errorMessage}</p></#if>
						</div>
						<div class="col-12 mt-1 text-center">
							<input type="submit" class="btn btn-outline-success" value="Search by price"/>
						</div>
				    </div>
			    </div>
			 </form>
		</div>
	</div>
	<#if books?has_content>
	<div class="row">
		<div class="pt-4 col-12">
			<p class="h5">Search results for books with a price equal to <span class="text-success">${price}$</span> or ${option}</p>
				<div class="row">
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

	<#else>
	<p class="text-danger">No books correspond to search, or search is empty</p>
	</#if>
</div>