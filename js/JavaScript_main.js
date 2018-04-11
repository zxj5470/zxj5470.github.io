if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'JavaScript_main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'JavaScript_main'.");
}
var JavaScript_main = function (_, Kotlin) {
  'use strict';
  function main(args) {
    alert('Hello world!');
  }
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('JavaScript_main', _);
  return _;
}(typeof JavaScript_main === 'undefined' ? {} : JavaScript_main, kotlin);
