### Design document (04-06-19) *What’s in your fridge?*
### Programmeerproject
#### By Levy van der Linde
#### Minor Programmeren
_____________________________________________________________________________________________________________

### Advanced sketches of the UI:
<img width="700" alt="image" src="https://user-images.githubusercontent.com/47352487/58882609-ebe39b00-86dc-11e9-8840-8b7773950920.png">

### A diagram of utility modes, classes and functions:
<img width="700" alt="image" src="https://user-images.githubusercontent.com/47352487/58882948-c6a35c80-86dd-11e9-9e57-fd541880c386.png">

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


## Database tables and fields 
-Email address/name to sign up

-Title of the recipe

-Name of the publisher

-Image url (possibly no url because user will want add their own image?)

-Ingredients of this recipe


### Discussion points:
-Searching option seperated for API & DB search? Or user can search in both at the same time

-Using SQlite on phone for user recipes?

-Same Recipe Class voor API & DB?

-Put recipes in listviews? (Adapter)


