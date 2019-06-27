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

<img width="200" alt="image" src="https://user-images.githubusercontent.com/47352487/60271941-83f72d80-98f3-11e9-9dbe-d3a014eda798.png">

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
There are two modelclasses 'SearchRecipe and 'GetRecipe' used for both the API and user recipes which represent the kind of recipe.

_Performing a SearchRequest_

First off the API and Usersrecipes are searched with a SearchRequest. The SearchRequest is performed after the search function
was used with a searchword. This request returns a response to the app with a recipes title, image_url and an ID. 
These are used to represent the results overview in ResultsActivity. I really wanted to show both the API- and Userrecipes at once 
in the ResultsActivity. So they are both performed and before showing the results on the app they are put in a list together. 
To actually put the data in the GridView in ResultsActivity, the app uses the ResultsAdapter. This adapter shows the recipes title 
and image and has a default image of the user icon. So the app only puts an image_url incase it's received from an API recipe.

_Locally filtering searchword_
Unafortunately it wasn't able to filter online in the rester database on the users searchword. I solved it by locally filtering the ingredients in my code instead of online throught the database url.

_Recipe tag_

To distinguish whether a recipe is from the API or Usersdatabase they get a recipe tag. The tag is called 'isAPI' and the value 
will be true of false depending the kind of recipe. (Ofcourse an API recipe tag value is 'true' and user recipe is 'false')

_GetRequest_

This recipetag is used whenever the user clicks on a recipe in the ResultsActivity. The recipes tag will be passed through the intent along with the recipes id. When the RecipeActivity wants to perform a GetRequest with the clicked recipe, it'll know whether to do an APIGetRequest or UserGetRequest with the given recipe tag and ID :)

So after the user has clicked on a recipe in ResultsActivity, the app will go to RecipeActivity and perform a GetRequest.
This GetRequest will ensure more details about a recipe, like it's title, image_url, ingredients and recipe(url). 
These will be shown in the RecipeActivity. In case the user clicked on an API recipe, there is a 'source_url' which is linked to the recipes online webpage. The button 'View recipe online' will redirect the user to that page. An user recipe on the other hand is based on the UploadActivity. The user puts all data in edit texts so the recipe will be returned as a string. That's why the 'view recipe online' button is invisible at an users recipe detailpage. Also it's possible to get a closer view by clicking on the recipes image. There is a method which makes a bigger format popup and can be closed(set invisible) by clicking on the cross button.

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

To give an proper comparison of the difference between [my first design](https://github.com/Lvdl1999/ProgrammeerProject/blob/master/DESIGN.md) and the final product I made a final sketch of the YourFridge app!

During the project I've described important changes and desicions in my [processbook](https://github.com/Lvdl1999/ProgrammeerProject/blob/master/PROCESS.md).

My first design and sketch was this:
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/47352487/58960665-eacb7000-87a7-11e9-9723-5efe525bedaa.png">

One month later, after a lot of hard work this is the total change:
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/47352487/60266856-37a6f000-98e9-11e9-952e-b728d8a2effa.png">


**Week 1:**

_Changing proposal_
First of all I started this project with a different proposal. I had this app idea but couldn't find a fitting API for it. After that I started brainstorming and came up with the idea about 'what's in your fridge?'.
My first idea was having two searchbars in SearchActivity because I wans't sure if it would succeed to put both SearchRequests results at once in ResultsActivity. Then I consulted with a TA and thought it out to make it happen.

_Loading recipes images_
When my API worked I realised it did load the recipes titles in the ResultsActivity but not the recipes images. I couldn't understand how because the titles did come through.
After trying a lot with Natasja I found out it was because of the url itself. The url was an 'http'- instead of 'https' link which is why it didn't work. I created a method which splits the url at the ':' and puts an 's' inbetween. 

**Week 2:**

_Working with API_
Working on both API requests went really well, I had a really good idea about how I wanted to use and implement it. In case I forgot something about the standards of a Request I used my older projects from AndroidStudio.
After both the SearchRequest and GetRequest for the API worked I started with the users recipes. I decided to go with the Rester Database because I have already used it once before. 

_Two search options to one_
At first I wanted to make two search options. One to search for users recipes and one for the API recipes. This wouldn't be a nice solution and certainly not very user friendly because it would require a user two search twice but seperately. After the data is received from both the APISearchRequest and the UserSearchRequest it's put together in one list which is shown in through the ResultsAdapter. I decided to make sure the user recipes always show first in the ResultsActivity because the API has an endless amount of recipes and that would outshine the user recipes if they came after. 

**Week 3:**

_Recipe tags risks_
I had to make something to make sure the RecipeActivity could recognize whether the user clicked on an user recipe or API recipe. I started off quite enthousiasmic but then I messed up and didn't get the code back as it was. So I had to go back to my last commit, which was were I left off the day before. But eventually I created the Recipe tag so the app knows to do the right request.

_Difference recipes_ 
During this week I realized the difficulty about the difference between working with the API recipes and userrecipes. 
- Think about the API recipe giving the ingredients in an Arraylist but the user uploads their ingredients as a String.
- API recipes come with an image_url and I didn't get at the option to add an image to your own recipe.
- Also do the API recipes have a source_url which links to the recipe online but the user recipes have to get the recipe as a String. To solve this problem I used the variable 'source' to pass a users recipe as a String.

Most of there topics are also because I decided to use 1 modelclass per request for API and user recipes as well. I did this after some consultation because it's supposed to be more decent for the code, but it might be a bit subjective. 

_User interface_
About the user interface, I decided to get rid of the MainActivity. I decided this after I got some feedback about the fact that only games have a start menu as opening screen. On this app it's most important to use the search option so that's what the user gets at first.
Also thought about what would happen if the app can't find any matching recipes with your searchword. I created a toastmessage to inform the user for this case. 

_Filter in rester database_
After asking the TA and trying a few things we realized it's not possible to filter the database with the searchword. This was quite unfortunate because it was the last thing I needed to complete the implementation of my app. I solved it by locally filtering the ingredients in my code instead of online throught the database url.

**Week 4:**

_User Interface_
This last week I focussed a lot at improving the UI. That's why I made a few changes and improvements:
- Added a floating button in ResultsActivity so the user go to the UploadActivity from there too.
- Changed to overall font style to one I downloaded called 'actor'
- Change the background of the gridview in ResultsActivity to create some more contrast to the recipes
- I worked on the logo of the app which is shown in SearchActiviy. The colors are fierced and stand out more (in my opinion). Because this is the mainscreen now, I felt like it should pop out once you open the app.
- The upload button changed because I didn't like the simple button with text in the overall screen. It became an icon which matches the rest a lot better.
- Creating a social media platform for my app! I created a button which redirects the user to the apps instagram.
- Changed the standard image from user recipes to an icon. Depending on the kind of recipe, there's a method that makes sure to load the image url when it's an API recipe. 

_Noticed while testing_
After having a friend testing my app I noticed she would click on the image in RecipeActivity. This made me think about an option to get a closer view on it. So I created a method when the user clicks on the image, a bigger version of it will become visible in the activity, along with a closing button. This way the user can have a better look at the recipes image.
Also she uploaded her own recipe, and when she pressed the button to upload, I noticed the edittexts don't clear automatically. I added this to the method so now the app handles this itself and the user is able to eventually add more recipes.






