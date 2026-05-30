// karate-config.js  –  Global Karate configuration
// This file is automatically loaded before every feature.
// It sets base URLs, common headers, and environment-specific variables.

function fn() {

    // ── Environment selector ──────────────────────────────────────────────────
    var env = karate.env; // reads -Dkarate.env system property
    if (!env) env = 'dev';
    karate.log('Running with karate.env =', env);

    // ── Base configuration object ──────────────────────────────────────────────
    var config = {
        env: env,
        baseUrl: 'https://api.demoblaze.com',

        // Default test user  (overridden in scenario data / CSV)
        defaultUser: {
            username: 'Mabel Perez',
            password: '123456'
        },

        // Shared HTTP settings
        connectTimeout: 10000,
        readTimeout:    10000
    };

    // ── Environment overrides ─────────────────────────────────────────────────
    if (env === 'staging') {
        config.baseUrl = 'https://api.demoblaze.com'; // same endpoint, different env marker
    }

    // ── Global HTTP client config ─────────────────────────────────────────────
    karate.configure('connectTimeout', config.connectTimeout);
    karate.configure('readTimeout',    config.readTimeout);

    // Globally accept JSON responses
    karate.configure('headers', { 'Content-Type': 'application/json', 'Accept': 'application/json' });

    return config;
}
