db = db.getSiblingDB("books");

db.createUser({
    user: "mongouser",
    pwd: "mongor00t",
    roles: [
      {
        role: 'readWrite',
        db: 'books'
      },
    ],
  });
