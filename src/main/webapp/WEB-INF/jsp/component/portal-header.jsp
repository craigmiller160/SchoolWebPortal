<!-- THE SPECIAL ITEMS FOR THE PORTAL PAGE HEADER -->
<!-- IMPORTANT - The JSTL, Bootstrap, Spring Tags, and JavaScript imports 
	are required for this and all components -->

<ul class="nav navbar-nav">
	<li class="dropdown">
		<a href="#" class="${headerTextStyle} dropdown-toggle"
			data-toggle="dropdown">
			<% //TODO internationalize text %>
			Courses <span class="caret"></span>
		</a>
		<ul class="dropdown-menu" id="courses-dropdown">
			<li>
				<a href="#">
					<% //TODO internationalize text %>
					View Course Catalog
				</a>
			</li>
			<sec:authorize access="hasRole('ADMIN')">
				<li>
					<a href="#">
						<% //TODO internationalize text %>
						Manage Course Catalog
					</a>
				</li>
			</sec:authorize>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" class="${headerTextStyle} dropdown-toggle"
			data-toggle="dropdown">
			<% //TODO internationalize text %>
			Students <span class="caret"></span>
		</a>
		<ul class="dropdown-menu" id="students-dropdown">
			<sec:authorize access="hasRole('STUDENT')">
				<li>
					<a href="#">
						<% //TODO internationalize text %>
						Manage Your Schedule
					</a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')">
				<li>
					<a href="#">
						<% //TODO internationalize text %>
						View Registered Students
					</a>
				</li>
				<li>
					<a href="#">
						<% //TODO internationalize text %>
						Manage Registered Students
					</a>
				</li>
			</sec:authorize>
		</ul>
	</li>
	<sec:authorize access="hasRole('MASTER')">
		<li class="dropdown">
			<a href="#" class="${headerTextStyle} dropdown-toggle"
				data-toggle="dropdown">
				<% //TODO internationalize text %>
				Accounts <span class="caret"></span>
			</a>
			<ul class="dropdown-menu" id="accounts-dropdown">
				<li>
					<a href="#">
						<% //TODO internationalize text %>
						Manage User Accounts
					</a>
				</li>
			</ul>
		</li>
	</sec:authorize>
</ul>