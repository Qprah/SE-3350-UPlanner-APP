# Bug Bounty Hunter - A01 Group4


# Iteration 2 Fixes

## HSQLDB
- Register/SignUp page:
    - Bug: The user information is not persistent i.e. when a user sign's up and logs back in the student number along with the year and courses changes.
    - Bug: Password change doesn't work (Shows success message but doesnt work).

- Login page:
    - Bug: The user information is not persistent i.e. when a user logs in (tested using seed user data), the student number along with the year and courses changes.
    - Bug: Password change doesn't work (Shows success message but doesnt work).

## Stub
- Register/SignUp page:
    - Data is persistent.
    - Password change does work.

- Login page:
    - Data is persistent (tested using seed user data and new User reigistration).
    - Password change does work (tested using seed user data and new User reigistration).


## Other bugs
- Fix Name box to accomodate longer names.
- Debug course history for all of the above 4 sections (doesn't work with any of those).
- Refactor code to remove files such as StudentAnmol and CourseAnmol
- Include the Graph for course history in UI.
    

