## Design document (04-06-19)
## Programmeerproject
### By Levy van der Linde
### Minor Programmeren

*What’s in your fridge?*

-Advanced sketches of the UI



-A diagram of utility modes, classes and functions




## Food2fork recipes
I’m going to use an API from ‘food2fork.com’. They offer an API which exposes recipe discovery functions. 
It gives access to a recipe database and ingredient search function

#### Search parameters:
For the Search Query the user should separate their ingredients by commas. After searching the recipes will be returned. 

#### Recipe details parameters:
The recipes will be returned with an unique ID which will be used to get desired recipe.

Any request returns 30 results max.

## Users recipes
Next to using recipes from this API the users can also add their own recipes. This will be handled through a rester service. A user can submit their email-address and add a recipe. The recipes will be stored online on a separate database. 




-A list of database tables and fields (and their types) if you will use a database
Waaruit bestaat de tabel als iemand een eigen recept submit?

Email address/name to sign up?
Title of the recipe
Name of the publisher
Image url (possibly no url because user will want add their own image?)
Ingredients of this recipe





