## FINAL REPORT
## Programmeerproject
### By Levy van der Linde
### Minor Programmeren

*What’s in your fridge?*
No inspiration on what to cook? This app recommends several recipes built on what’s in your fridge!

[YOURFRIDGE APP](https://www.instagram.com/yourfridge_app/)


### Application
My application is called 'YourFridge'. The main functionality is being able to search for recipes,
based on a single or few searchwords. It's for those days were you don't have any inspiration on what you
should eat (or even drink). With my app it's also possible to add your own recipes to the database so other 
users can enjoy your recipes.

![image](https://user-images.githubusercontent.com/47352487/60256538-0a9b1300-98d2-11e9-8a3b-4eb37e5f619e.png)

### Technical design

The app consists of 4 screens in total: SearchActivity, ResultsActivity, RecipeActivity and an UploadActivity.

**High level overview**
Starting off with the SearchActivity. From here the user can start searching recipes. Once the searchword is 
given and the users clicks 'search', the app will show an overview of recipes in the ResultsActivity. 
From this overview it's possible to get more details about a recipe by clicking on it. The details of the recipe like:
a bigger image, recipe, ingredients will be shown in the RecipeActivity. Back to the SearchActivity, there is two more options. 
One of them is the upload button, from here the user goes to the UploadActivity were it's possible to upload your own recipe. 
By filling all textfields and clicking 'Upload Recipe', it will be uploaded to the database. When your searching for recipes 
with a searchword that matches an ingredients from the user recipe, it will show in the ResultsActivity too.
Finally the other option from SearchActivity is the Instagram button. This will redirect you to the Instagram Page of the YourFridge app :)

**Details about my code**
Next to the screens, there is a lot behind the scenes. 
This app uses an API called ['Food2Fork'](https://www.food2fork.com) and also a local host called ['the Rester Database'](https://github.com/stgm/rester).

To use the API's data, this app uses 2 kinds of request. The *'SearchRequest'* and *'GetRequest'*.

_Performing a SearchRequest_

First off the API and Usersrecipes are searched with a SearchRequest. The SearchRequest is performed after the search function
was used with a searchword. This request returns a response to the app with a recipes title, image_url and an ID. 
These are used to represent the results overview in ResultsActivity. I really wanted to show both the API- and Userrecipes at once 
in the ResultsActivity. So they are both performed and before showing the results on the app they are put in a list together. 
To actually put the data in the GridView in ResultsActivity, the app uses the ResultsAdapter. This adapter shows the recipes title 
and image and has a default image of the user icon. So the app only puts an image_url incase it's received from an API recipe.

_Locally filtering searchword_

-
-
-
--




_Recipe tag_

To distinguish whether a recipe is from the API or Usersdatabase they get a recipe tag. The tag is called 'isAPI' and the value 
will be true of false depending the kind of recipe. (Ofcourse an API recipe tag value is 'true' and user recipe is 'false')

_GetRequest_

This recipetag is used whenever the user clicks on a recipe in the ResultsActivity. The recipes tag will be passed through the intent
along with the recipes id. When the RecipeActivity wants to perform a GetRequest with the clicked recipe, it'll know whether to 
do an APIGetRequest or UserGetRequest with the given recipe tag and ID :)

So after the user has clicked on a recipe in ResultsActivity, the app will go to RecipeActivity and perform a GetRequest.
This GetRequest will ensure more details about a recipe, like it's title, image_url, ingredients and recipe(url). 
These will be shown in the RecipeActivity. In case the user clicked on an API recipe, there is a 'source_url' which is linked to the 
recipes online webpage. The button 'View recipe online' will redirect the user to that page. An user recipe on the other hand is based 
on the UploadActivity. The user puts all data in edit texts so the recipe will be returned as a string. That's why the 'view recipe online' 
button is invisible at an users recipe detailpage.

_Upload your own recipe!_

Whether the user clicks on the upload icon in the SearchActivity or ResultsActivity, the app will redirect to the UploadActivity.
In the UploadActivity there are a few edittexts which will represent your recipe. The user has to fill in their name, emailadress,
the title of their recipe, it's ingredients and ofcourse the recipe itself. When clicking on the button 'Upload recipe', it'll check if 
all fields are filled. If so, the recipe will be uploaded to the rester database and the app gives a toast message to inform the 
user it succeeded.
If the user left a field blank, the app will return a toastmessage to remind them about it before the app can upload the users recipe.
To upload the recipe to the resterdatabase all parameters are put in a new request. After it has been successfully uploaded, all edit 
text fields will return blank so the user could add more recipes if that's wanted.

_Find your own!_

After your recipe was uploaded to the rester database, it's now possible to find your own recipe on the app!
Use an ingredient from your own recipe to find it :)






### Challenges and development


Samenvoegen van users en api recepten

Users recepten als eerste boven aan altijd omdat api eindeloos veel recepten nog heeft dus zou je ze nooit tegenkomen




