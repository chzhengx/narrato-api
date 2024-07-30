package narrato.users.usecases.updateuser;

record UpdateUserCmd(String email, String username, String password, String bio, String image) {}
