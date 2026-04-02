import 'package:flutter/material.dart';
import 'mushaf_image_screen.dart';

class QuranScreen extends StatelessWidget {
  const QuranScreen({super.key});

  final List<Map<String, dynamic>> _surahs = const [
    {'id': 1, 'name': 'الفاتحة', 'verses': 7, 'type': 'مكية', 'page': 1},
    {'id': 2, 'name': 'البقرة', 'verses': 286, 'type': 'مدنية', 'page': 2},
    {'id': 3, 'name': 'آل عمران', 'verses': 200, 'type': 'مدنية', 'page': 50},
    {'id': 18, 'name': 'الكهف', 'verses': 110, 'type': 'مكية', 'page': 293},
    {'id': 36, 'name': 'يس', 'verses': 83, 'type': 'مكية', 'page': 440},
    {'id': 67, 'name': 'الملك', 'verses': 30, 'type': 'مكية', 'page': 562},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.fromLTRB(20, 60, 20, 20),
      itemCount: _surahs.length + 1,
      itemBuilder: (context, index) {
        if (index == 0) {
          return const Padding(
            padding: EdgeInsets.only(bottom: 20),
            child: Text('فهرس السور', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF0F4C3A))),
          );
        }
        final surah = _surahs[index - 1];
        return Card(
          color: Colors.white, // كارت أبيض ناصع
          elevation: 2,
          shadowColor: const Color(0xFF0F4C3A).withOpacity(0.2),
          margin: const EdgeInsets.only(bottom: 12),
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15)),
          child: ListTile(
            contentPadding: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
            leading: CircleAvatar(
              backgroundColor: const Color(0xFF0F4C3A).withOpacity(0.1),
              child: Text('${surah['id']}', style: const TextStyle(color: Color(0xFF0F4C3A), fontWeight: FontWeight.bold)),
            ),
            title: Text(surah['name'], style: const TextStyle(color: Color(0xFF0F4C3A), fontSize: 20, fontWeight: FontWeight.bold)),
            subtitle: Text('${surah['type']} • آياتها ${surah['verses']}', style: const TextStyle(color: Colors.grey)),
            trailing: const Icon(Icons.arrow_forward_ios, color: Color(0xFFD4AF37), size: 16), // سهم ذهبي
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context) => MushafImageScreen(startPage: surah['page'])));
            },
          ),
        );
      },
    );
  }
}
