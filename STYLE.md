## Styleguide
## Programmeerproject
### By Levy van der Linde
### Minor Programmeren

*What’s in your fridge?*


### Indentation
All indents are four spaces. All indenting is done with tabs. 
Also the matching braces always line up vertically to each other. 

### Class and function titles
All names should be written in english.
All class and interface identifiers start and are seperated with a capital letter. 
Other identifiers like fiels, local variables, methods and parameters will start with 
an uppercase or lowercase depending the kind of method. Regular methods start with a 
lowercase but request methods are with uppercase.

### Variables
Variable names should be short and simple but also meaningful. The name is not only used for programming but 
also to declare whats happening in the code to the reader.

### Comments
Short comments are made to explain whats happening in the code but limited. They will apear 
on a single line indented to the level of the code below.

### Class Member Ordering

Open brace "{" appears at the next line from the declaration statement.
Closing brace "}" starts a line by itself indented to match it's opening statement vertically.

class Order

{
    // fields

    // constructors

    // methods
}

### Maximum Line Length
I'll try to avoid lines longer than 86 characters.

### Access
All fields must be private, only except for some constants.


### Loops
A for statement should have the following form:
for (initialization; condition; update) 

{ 

  statements;
  
}

A while statement should have the following form:
while (condition) 

{ 

  statements;
  
}

A switch statement should have the following form:

switch (condition) 

{ 

case A:

  statements;
  /* falls through */
   
case B:

  statements; 
  break;

case C: 

  statements;
  break;

default: 

  statements;
  break; 
  
}


