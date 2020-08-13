# Cache simulation

## SoftHashMap
Implementation based of *WeakHashMap* using Soft References instead of Weak References to achieve Cache-like capabilities to free memory elements in case of heap space shortage. 


## Cache
Implemented around SoftHashMap, fully synchronized to allow multi threaded access.. Contains *long* type key and an Array of integer elements. Includes hit percentage monitoring.  

## Multi-threaded sorting
Given number of threads sorts elements in cache using various algorithms which are dynamically loaded using reflection mechanism. This allows to add/remove algorithm-classes even during programs work. 

*Before using - dynamic loading requires to compile the sorters classes beforehand and place them in selected class folder*

