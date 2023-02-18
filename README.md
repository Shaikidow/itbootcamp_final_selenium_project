This project is a series of automated regression tests for the page https://vue-demo.daniel-avellaneda.com.

The test classes can be executed in any order.
All test classes (sans AuthRoutesTests and LocaleTests) check for correct URL and correct input types at the beginning.
The page and tests for the /admin/users route have been omitted for now, due to privacy concerns.

IMPORTANT NOTE:
The provided website exists for test purposes and as such needs to have its databases reset on a daily basis.
This means that tests might crash during maintenance hours, especially if they require super administrator privileges.