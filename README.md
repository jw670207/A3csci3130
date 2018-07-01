# A3csci3130

CSCI 3130 Assignment3  
This is simple application of CRUD forms for Firebase Realtime database 


## Libraries
**joda-time-2.10.jar**  
This app used joda time library for calculating the estimated time as the unique numeral key.  
Before using Espresso test, I made all codes based on android sdk 25. Therefore, I used this library for working on low level sdk version.  
[Joda Time website](http://www.joda.org/joda-time/)

## Firebase Rulere  
For provinces and business, Spinner was used. As a result, these elements didn't, actually, needed to use specified rules.
Therefore, I set up a simple rule for province element.  
```javascript
{  
  "rules": {  
    ".read": "true",  
    ".write": "true",  
    "contacts": {  
      "$contact": {  
        "bid": {  
          ".validate": "newData.isString() && newData.val().length==9 && newData.val().matches(/^[0-9]{9}$/)"  
        },  
        "name": {  
          ".validate": "newData.isString() && newData.val().length >= 2 && newData.val().length <= 48"  
        },  
        "email": {  
          ".validate": "newData.isString() && newData.val().matches(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$/i)"  
        },  
        "address": {  
          ".validate": "newData.isString() && newData.val().length <50"  
        },  
        "business": {  
          ".validate": "newData.isString() && newData.val().matches(/^(Fisher|Distributor|Processor|Fish Monger)$/)"  
        },  
        "province": {  
          ".validate": "newData.isString() && newData.val().length <=2"   
        }  
      }  
    }  
  }  
}  
```
## Screenshot  
![alt text](https://github.com/jw670207/A3csci3130/master/screenshot.jpeg "App Screenshot")  

## References  
**email validation rule** - Firebase Security Rules Regular Expressions  
[Link](https://firebase.google.com/docs/reference/security/database/regex)
  
