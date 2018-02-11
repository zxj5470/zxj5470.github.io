if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Blog'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Blog'.");
}
var Blog = function (_, Kotlin) {
  'use strict';
  function main(args) {
    alert('Hello world!');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('Blog', _);
  return _;
}(typeof Blog === 'undefined' ? {} : Blog, kotlin);
