<template>
  <div class="SignupComponent">
    <form @submit="signupUser" className="signupPageContainer">
      <h1>Sign Up</h1>
      <p>Sign up with your e-mail and password</p>
      <hr />
      <label for="email">E-mail</label>
      <input v-model="email" required name="email" type="email" />
      <label for="username">Username</label>
      <input v-model="username" required name="username" type="text" />
      <label for="password">Password</label>
      <input v-model="password" required name="password" type="password" />
      <div className="formButtons">
        <button type="submit">Sign Up</button>
      </div>
      <small @click="$emit('switchPage')" class="formSmall" href="/signup"
        >Already have an acoount? Log in here</small
      >
    </form>
  </div>
</template>

<script>
export default {
  name: "SignupComponent",
  data() {
    return {
      username: "",
      email: "",
      password: "",
    };
  },
  computed: {},
  methods: {
    async signupUser(e) {
      e.preventDefault();

      const user = {
        username: this.username,
        email: this.email,
        password: this.password,
      };

      this.$store.dispatch("signupUser", user);
    },
  },
};
</script>

<style scoped>
.signupPageContainer {
  display: flex;
  border: 3px solid hsl(236, 33%, 92%);
  flex-direction: column;
  padding: 2vw 2vw;

  border-radius: 1vw;
}
@media screen and (max-width: 768px) {
  .signupPageContainer h1 {
    font-size: 7.5vw !important;
  }
  .signupPageContainer p {
    font-size: 4vw !important;
  }
  .signupPageContainer label {
    font-size: 4vw !important;
  }
  .signupPageContainer input {
    font-size: 5vw !important;
    height: 8vw !important;
  }
  .signupPageContainer button {
    font-size: 5vw !important;
  }
  .signupPageContainer a {
    font-size: 3vw !important;
    margin-top: 2vw !important;
  }
}
.signupPageContainer {
  background-color: white;
}

.signupPageContainer h1 {
  color: hsl(235, 19%, 35%);
}
.signupPageContainer h1 {
  font-size: 3vw;
}

.signupPageContainer p {
  color: hsl(236, 33%, 80%);
}
.signupPageContainer p {
  margin-top: 0.5vw;
  margin-bottom: 0.5vw;
  font-size: 2vw;
  font-weight: bold;
}

.signupPageContainer hr {
  margin-bottom: 1vw;
}

.signupPageContainer label {
  color: hsl(235, 19%, 35%);
}
.signupPageContainer label {
  font-size: 2vw;
  margin-bottom: 0.3vw;
  margin-top: 1vw;
}

.signupPageContainer input {
  color: hsl(235, 19%, 35%);
  background-color: hsl(236, 33%, 92%);
}
.signupPageContainer input {
  font-size: 2.5vw;
  height: 4.5vw;
  padding-left: 0.5vw;
  font-weight: bold;
  border-radius: 1vw;
  border: none;
  outline: none;
}

.signupPageContainer input:focus {
  outline: 0.2vw solid hsl(235, 19%, 35%);
}

.formButtons {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 2vw;
}

.signupPageContainer .formButtons button {
  color: hsl(235, 19%, 35%);
  border: 0.2vw solid hsl(235, 19%, 35%);
}
.formButtons button {
  background-color: transparent;
  font-size: 2vw;
  border-radius: 1vw;
  padding: 0.5vw 1vw;
  cursor: pointer;
  display: flex;

  margin: auto;
  margin-bottom: 1vw;
  font-weight: bold;
}
.formButtons button .icon {
  margin-left: 1vw;
}

.signupPageContainer .formButtons button:hover {
  background-color: hsl(235, 19%, 35%);
  color: hsl(236, 33%, 92%);
}

.formSmall {
  align-self: center;
  margin-top: 1vw;
  font-size: 2vw;
  cursor: pointer;
}

.formSmall:hover {
  color: rgb(72, 72, 255) !important;
}
</style>
