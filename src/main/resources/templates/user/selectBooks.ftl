<div class="form-group col-12">
    <label for="books">Books owned</label>
    	<ul>
    	<#assign loop = 0>
    	<#list books as key, value>

    	<li>
    		<input class="form-check-input" type="checkbox" id="book${key}" value="${key}" name="listOfBookIds[${loop}]">
			<label class="form-check-label" for="book${key}">${value}</label>
		</li>
		<#assign loop = loop + 1>
		</#list>
		</ul>
  </div>