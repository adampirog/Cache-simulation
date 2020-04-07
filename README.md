# Cache simulation

## SoftHashMap
Implementation based of *WeakHashMap* using Soft References instead of Weak References to achieve Cache-like capabilities to free memory elements in case of heap space shortage. 


## Cache
Implemented using SoftHashMap. Contains *long* type key and an Array of integer elements. Includes hit percentage monitoring.  Synchronized to allow multi threaded access.

## Multi-threaded sorting
Number of threads sorts elements in cache using various algorithms which are dynamically loaded using reflection mechanism. This allows to add/remove algorithm-classes even during programs work. 

