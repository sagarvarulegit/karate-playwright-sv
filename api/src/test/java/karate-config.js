function fn() {
  var env = karate.env || 'dev';
  
  karate.log('Karate environment is:', env);

  var config = {
    baseUrl: 'https://reqres.in/api'
  };

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  karate.configure('headers', { 'x-api-key': 'reqres-free-v1' });

  return config;
}
