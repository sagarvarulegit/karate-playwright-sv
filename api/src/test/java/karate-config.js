function fn() {
  var env = karate.env || 'dev';
  
  karate.log('Karate environment is:', env);

  var config = {
    baseUrl: 'https://reqres.in/api',
    apiKey: 'reqres-free-v1'
  };

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);

  return config;
}
