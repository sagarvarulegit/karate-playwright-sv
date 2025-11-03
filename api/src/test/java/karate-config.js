function fn() {
  // This function returns a JSON object with your global configuration
  // 'karate.env' is a system property you can set (e.g., -Dkarate.env=staging)
  var env = karate.env || 'dev'; // default to 'dev' if not set
  
  karate.log('Karate environment is:', env);

  var config = {
    // This 'baseUrl' variable will be available in all your feature files
    baseUrl: 'https://reqres.in/api',
    apiKey: 'reqres-free-v1'
  };

  // You could have environment-specific logic here
  // if (env === 'staging') {
  //   config.baseUrl = 'https://staging.reqres.in/api';
  // } else if (env === 'prod') {
  //   config.baseUrl = 'https://reqres.in/api';
  // }

  // Configure timeouts (optional, but good practice)
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);

  return config;
}
