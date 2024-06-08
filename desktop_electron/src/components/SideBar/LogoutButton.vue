<template>
  <button @click="logout">Log out</button>
</template>

<script>
export default {
  name: "LogoutButton",

  data() {
    return {};
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>
<style scoped>
button {
  height: 3rem;
  width: 100%;
  align-self: flex-end;
  padding: 0rem 1rem;
  border-radius: 8px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  background-color: rgb(234, 89, 89);
  color: white;
  border: none;
  transition: all 0.15s ease-out;
}
button:hover {
  color: rgb(234, 89, 89);
  border: 2px solid rgb(234, 89, 89);
  background-color: transparent;
}
</style>
