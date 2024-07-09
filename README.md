# Refactoring of Existing Java Project

The primary goal of this refactoring effort is to reengineer a Java application. The are two commits(before and after refactoring).

The general purpose of the application is to manage the 
sales made and the commissions attributed to the sales representatives of a small clothing company.

## Refactoring Techniques Used

- **Delete Lazy Classes**: Lazy Classes that do not do much to deserve to exist can be deleted.

- **Extract Method**: Break down large methods into smaller, more manageable ones to improve readability and maintainability.
  
- **Rename Variables and Classes**: Use meaningful names that accurately describe their purpose and functionality.
  
- **Eliminate Code Duplication**: Identify duplicated code segments and refactor them into reusable functions or classes.
  
- **Optimize Data Structures**: Replace Java Vector data structure which is thread-safe and slow with ArrayList.

- **Remove Primitive Obsession**: Use Objects as fields instead of large variety of primitive fields.

- **Template Method**: Define the skeleton of an algorithm in the superclass and let subclasses override specific steps of the algorithm without changing its structure.
